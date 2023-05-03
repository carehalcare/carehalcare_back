package carehalcare.carehalcare.dto.board.walk;

import carehalcare.carehalcare.domain.board.walk.WalkImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Getter
@NoArgsConstructor
public class WalkImageResponseDto {
    private Long id;
    private String originalFilename;
    private String storeFilename;
    private String filePath;
    private Long walkId;
    private String encodedString;

    public WalkImageResponseDto(WalkImage walkImage){
        this.id=walkImage.getId();
        this.originalFilename=walkImage.getOriginalFilename();
        this.storeFilename=walkImage.getStoreFilename();
        this.filePath=walkImage.getFilePath();
        this.walkId=walkImage.getWalk().getId();

        try{
            InputStream inputStream = new FileInputStream(this.filePath);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            this.encodedString = Base64.getEncoder().encodeToString(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
