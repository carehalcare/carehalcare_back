package carehalcare.carehalcare.domain.board.walk;

import carehalcare.carehalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class WalkImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="walkImageId")
    private Long id;

    private String originalFilename;

    private String storeFilename;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "walkId")
    private Walk walk;

    @Builder
    public WalkImage(String originalFilename, String storeFilename, String filePath){
        this.originalFilename=originalFilename;
        this.storeFilename=storeFilename;
        this.filePath=filePath;
    }

    public void setWalk(Walk walk){
        this.walk=walk;
        if(!walk.getWalkImage().contains(this)){
            walk.getWalkImage().add(this);
        }
    }
}
