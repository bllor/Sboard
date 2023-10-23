package kr.co.sboard.dto;

import kr.co.sboard.entity.FileEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileDTO {

    private int fno;
    private int ano;
    private String ofile;
    private String sfile;
    private int download;

    @CreationTimestamp
    private LocalDateTime rdate;

    public FileEntity toEntity(){
        return FileEntity.builder()
                .fno(fno)
                .ano(ano)
                .ofile(ofile)
                .sfile(sfile)
                .download(download)
                .rdate(rdate)
                .build();
    }

}
