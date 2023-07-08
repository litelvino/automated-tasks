//1 - ler os arquivos(pastas) dentro da pasta
//2 - para cada arquivo entrar na pasta
//3 - verificar se existe arquivo de media
//4 - se existir renomear para o nome da pasta
//5 - mover arquivo de media para pasta especificada

//6 - e se a pasta tiver varios arquivos
//7 - deletar pasta antiga

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SearchFileMoveToFoder {

    private final static List<String> VIDEO_EXTENSIONS = List.of(".mp4", ".mkv", ".avi", ".ts");
    private final static Path FOLDER = Paths.get("D:\\qBittorrentDone\\");
    private final static String FOLDER_FILE_MOVE = "D:\\VideosPRNew\\";

    public static void main(String[] args) {
        List<File> files = getFilesFolder(FOLDER);
        files.forEach(file -> {
            List<File> filesFolder = getFilesFolder(file.toPath());
            filesFolder.forEach(fileFolder -> fileToMove(FOLDER_FILE_MOVE, fileFolder, file));
        });
    }

    private static void fileToMove(String folderFileMove, File fileFolder, File file) {
        String fileExtension = getFileExtension(fileFolder.getName());
        if (fileExtension != null && isExtensionVideo(fileExtension, VIDEO_EXTENSIONS)) {
            String fileName = fileName(folderFileMove, file.getName(), fileExtension);
            moveFileNewFolder(fileFolder, fileName);
        }
    }

    private static void moveFileNewFolder(File fileFolder, String fileName) {
        if (fileFolder.renameTo(new File(fileName))) System.out.println("Arquivo movido e renomeado!");
        else System.out.println("Falha ao renomear o arquivo!");
    }

    public static List<File> getFilesFolder(Path uriFolder) {
        File file = new File(uriFolder.toUri());
        if (!file.exists()) return Collections.emptyList();
        if (file.listFiles() == null) return Collections.emptyList();
        return Arrays.asList(Objects.requireNonNull(file.listFiles()));
    }

    public static String fileName(String directoryMove, String fileName, String extension) {
        return directoryMove + fileName + extension;
    }

    public static String getFileExtension(String fileName) {
        if (fileName == null) return null;
        int lastIndexOfPoint = fileName.lastIndexOf('.');
        if (lastIndexOfPoint < 0) return null;
        return fileName.substring(lastIndexOfPoint);
    }

    public static Boolean isExtensionVideo(String fileExtension, List<String> videoExtensions) {
        return videoExtensions.contains(fileExtension);
    }
}
