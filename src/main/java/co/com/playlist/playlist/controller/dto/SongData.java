package co.com.playlist.playlist.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class SongData {

    @NotEmpty
    @NonNull
    private String titulo;

    @NotEmpty
    @NonNull
    private String artista;

    @NotEmpty
    @NonNull
    private String album;

    @NotEmpty
    @NonNull
    private String anno;

    @NonNull
    private String genero;
}
