package com.example.caption.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.example.caption.grpc.caption.CaptionReply;
import com.example.caption.grpc.caption.CaptionRequest;
import com.example.caption.grpc.caption.CaptionServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.tomcat.util.Diagnostics;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequestMapping("/caption")
@CrossOrigin(origins = "*")
@RestController
public class CaptionController {

    @Value("${file.upload.rootDir}")
    private String uploadRootDir;

    @Autowired
    private StringRedisTemplate redisClient;

    private final Digester md5 = new Digester(DigestAlgorithm.MD5);

    @GrpcClient("captionService")
    private CaptionServiceGrpc.CaptionServiceBlockingStub captionService;

    @GrpcClient("captionChineseService")
    private CaptionServiceGrpc.CaptionServiceBlockingStub captionChineseService;

    @PostMapping("/captionByFile")
    public ResponseEntity<Map<String, Object>> getCaptionByFile(MultipartFile file, String lang) {
        Map<String, Object> response = new HashMap<>();
        String orgFilename = file.getOriginalFilename();

        if (lang == null) lang = "en";
//        long size = file.getSize();
//        log.debug("filename: {}.", filename);
//        log.debug("file size: {}.", size);
//        String ext = orgFilename.substring(orgFilename.lastIndexOf('.'));
        String newFilename = UUID.randomUUID().toString() + ".jpg";


        File saveDir = getSaveDir();

        File savePath = new File(saveDir, newFilename);
        try {
            // 压缩图像并且保存
            BufferedImage img = ImageIO.read(file.getInputStream());
            Thumbnails.of(img)
                    .size(img.getWidth(), img.getHeight())
                    .outputFormat(".jpg")
                    .toFile(savePath);
//            file.transferTo(savePath);
        } catch (IOException e) {
//            throw new RuntimeException("文件保存出现错误！", e);
            response.put("message", "文件保存出现错误！");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String hashcode = md5.digestHex(savePath);
        hashcode += md5.digestHex(lang);
        if (Boolean.TRUE.equals(redisClient.hasKey(hashcode))) {
//            String caption = redisClient.boundValueOps(hashcode).get();
            String caption = (String) redisClient.boundHashOps(hashcode).get("caption");
            String url = (String) redisClient.boundHashOps(hashcode).get("url");
            log.info("cache命中！");
            response.put("message", "cache命中！");
            response.put("caption", caption);
            response.put("url", url);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        String imgPath = savePath.getAbsolutePath();
        log.debug("image save to: {}.", imgPath);
        String url = "/images/" + savePath.getParentFile().getName() + "/" + savePath.getName();
        response.put("url", url);

        String caption = getCaption(imgPath, lang);
        response.put("message", "调用rpc生成！");
        response.put("caption", caption);

        // 存储到缓存
        redisClient.boundHashOps(hashcode).put("caption", caption);
        redisClient.boundHashOps(hashcode).put("url", url);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/captionByURL")
    public ResponseEntity<Map<String, Object>> getCaptionByURL(String url, String lang) {

        Map<String, Object> response = new HashMap<>();
        if (lang == null) lang = "en";

        // 计算url的md5码，查看缓存是否存在
        String hashcode = md5.digestHex(url);
        hashcode += md5.digestHex(lang);
        if (Boolean.TRUE.equals(redisClient.hasKey(hashcode))) {
            log.info("cache 命中！");
//            String caption = redisClient.opsForValue().get(hashcode);
            String caption = (String) redisClient.boundHashOps(hashcode).get("caption");
            String imageUrl = (String) redisClient.boundHashOps(hashcode).get("url");
            response.put("message", "cache命中！");
            response.put("caption", caption);
            response.put("url", imageUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


        // 生成保存的路径
//        String ext = url.substring(url.lastIndexOf('.'));
        // 压缩后的图像总是jpg
        String newFilename = UUID.randomUUID().toString() + ".jpg";
        File saveDir = getSaveDir();
        File savePath = new File(saveDir, newFilename);

        try {
            BufferedImage img = ImageIO.read(new URL(url));
            Thumbnails.of(img)
                    .size(img.getWidth(), img.getHeight())
                    .outputFormat(".jpg")
                    .toFile(savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 下载文件到本地
//        try (InputStream is = new URL(url).openStream();
//             OutputStream os = Files.newOutputStream(savePath.toPath())) {
//            IOUtils.copy(is, os);
//        } catch (MalformedURLException e) {
//            response.put("message", "url出现错误！");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (IOException e) {
//            response.put("message", "下载文件出现错误！");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//            throw new RuntimeException("根据url下载文件出现错误！", e);
        String imageUrl = "/images/" + savePath.getParentFile().getName() + "/" + savePath.getName();

        String imgPath = savePath.getAbsolutePath();
        String caption = getCaption(imgPath, lang);
        response.put("message", "调用rpc生成！");
        response.put("caption", caption);
        response.put("url", imageUrl);

        // 存储到缓存中
        redisClient.boundHashOps(hashcode).put("caption", caption);
        redisClient.boundHashOps(hashcode).put("url", imageUrl);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String getCaption(String imgPath, String lang) {
        CaptionRequest request = CaptionRequest.newBuilder().setImgPath(imgPath).build();
        CaptionReply reply = null;
        if ("zh".equals(lang)) {
            reply = captionChineseService.getCaption(request);
        } else if ("en".equals(lang)) {
            reply = captionService.getCaption(request);
        } else {
            throw new RuntimeException("不支持的语言类型：" + lang);
        }
        return reply.getCaption();
    }

    private File getSaveDir() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File saveDir = new File(uploadRootDir, date);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        return saveDir;
    }

//    public static void main(String[] args) {
//        File saveDir = new CaptionController().getSaveDir();
//    }
}
