package carehalcare.carehalcare.domain.board.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
/* 파일 저장하는 데 필요한 메소드 */
public class MealFileHandler {
    private final MealImageRepository mealImageRepository;

    @Value("${file.dir}/") // application.properites
    private String fileDirPath;

    /* 확장자 추출 */
    private String extractExt(String originalFilename){
        int idx = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(idx);
        return ext;
    }

    /* 저장할 파일 이름 구성 */
    private String createStoreFilename(String originalFilename){
        String uuid = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String curDate = now.format(dateTimeFormatter);
        String ext = extractExt(originalFilename);
        String storeFilename = curDate + "_" + uuid + ext;
        return storeFilename;
    }

    /* 파일 경로 구성 */
    public String createPath(String storeFilename){
        String viaPath = "images/mealImages/";
        return fileDirPath + viaPath + storeFilename;
    }

    /* 파일 저장 로직 */
    public MealImage storeFile(MultipartFile multipartFile) throws IOException{
        if (multipartFile.isEmpty()){
            System.out.println("파일이 존재하지 않습니다.");
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        String filePath = createPath(storeFilename);
        multipartFile.transferTo(new File(filePath));

        return mealImageRepository.save(MealImage.builder()
                        .originalFilename(originalFilename)
                        .storeFilename(storeFilename)
                        .filePath(filePath)
                        .build());
    }

    /* 전체 파일 저장 */
    public List<MealImage> storeFiles(List<MultipartFile> multipartFiles) throws IOException{
        List<MealImage> mealImages = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if (!multipartFile.isEmpty()){
                mealImages.add(storeFile(multipartFile));
            }
        }
        return mealImages;
    }
}
