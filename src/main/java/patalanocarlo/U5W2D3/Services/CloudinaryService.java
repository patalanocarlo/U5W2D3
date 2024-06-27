package patalanocarlo.U5W2D3.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadAvatar(MultipartFile file) throws  IOException {
  String url= cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap().get("url"));
  return url;
    }
}