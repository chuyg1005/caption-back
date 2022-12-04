package grpc;

import com.example.caption.grpc.caption.CaptionReply;
import com.example.caption.grpc.caption.CaptionRequest;
import com.example.caption.grpc.caption.CaptionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientTest {
    public static void main(String[] args) {
        String host = "njunlp.club";
        int port = 1024;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).build();

        CaptionServiceGrpc.CaptionServiceBlockingStub stub = CaptionServiceGrpc.newBlockingStub(channel);

        CaptionRequest request = CaptionRequest.newBuilder().setImgPath("/home/mahc/MSCOCO/coco_image2014/COCO_train2014_000000069616.jpg").build();

        CaptionReply reply = stub.getCaption(request);

        System.out.println(reply.getCaption());
    }
}
