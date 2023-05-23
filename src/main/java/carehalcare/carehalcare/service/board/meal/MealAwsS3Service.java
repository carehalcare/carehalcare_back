package carehalcare.carehalcare.service.board.meal;

import carehalcare.carehalcare.domain.board.meal.MealImage;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MealAwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    /* 파일 업로드 */
    public List<MealImage> uploadFile(List<MultipartFile> multipartFiles){
        List<MealImage> fileNameList = new ArrayList<>();

        multipartFiles.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()){
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            MealImage mealImage = new MealImage(file.getOriginalFilename(), fileName,
                    amazonS3.getUrl(bucket, fileName).toString());
            fileNameList.add(mealImage);
        });
        return fileNameList;
    }

    /* 파일명 생성 */
    public String createFileName(String fileName){
        String uuid = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String curDate = now.format(dateTimeFormatter);
        String ext = getFileExtension(fileName);
        String storeFilename = curDate + "_" + uuid + ext;
        return storeFilename;
    }

    /* 파일 확장자 추출 */
    private String getFileExtension(String fileName){
        // file 형식이 잘못된 경우를 확인
        // 파일 타입과 상관없이 업로드 가능 ("."의 존재 유무만 판단)
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
        }
    }

    /* 파일 조회 */
    public String getFile(String fileName){
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    /* 파일 삭제 */
    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        System.out.println(bucket);
    }
}
