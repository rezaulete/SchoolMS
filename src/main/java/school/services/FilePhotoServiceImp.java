package school.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FilePhotoServiceImp implements FilePhotoService {

	private static String UPLOAD_FOLDER = "src//main//resources//static//images//FilePhoto//";
	
	@Override
	public void UploadFile(MultipartFile file) throws Exception {
		
		Instant instant = Instant.now(); 
		String instanttime=instant.toString();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER +instanttime+file.getOriginalFilename());			
		try {
 			Files.write(path, bytes);

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		
	}



}
