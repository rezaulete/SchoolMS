package school.services;


import org.springframework.web.multipart.MultipartFile;


public interface FilePhotoService {


	public void UploadFile(MultipartFile file) throws Exception;


}
