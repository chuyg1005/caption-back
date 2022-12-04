package grpc;

import com.example.caption.CaptionApplication;
import com.example.caption.grpc.caption.CaptionReply;
import com.example.caption.grpc.caption.CaptionRequest;
import com.example.caption.grpc.caption.CaptionServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CaptionApplication.class)
public class GrpcSpringTest {
    @GrpcClient("captionService")
    private CaptionServiceGrpc.CaptionServiceBlockingStub stub;

    @Test
    public void testCaptionService() {

        CaptionRequest request = CaptionRequest.newBuilder().setImgPath("/home/mahc/MSCOCO/coco_image2014/COCO_train2014_000000069616.jpg").build();

        CaptionReply reply = stub.getCaption(request);

        System.out.println(reply.getCaption());
    }
}
