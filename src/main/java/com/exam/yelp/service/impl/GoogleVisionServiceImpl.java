package com.exam.yelp.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.exam.yelp.ImageDetectorException;
import com.exam.yelp.model.ImageDetailsModel;
import com.exam.yelp.service.ImageDetectorService;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service("googleVision")
@Primary
public class GoogleVisionServiceImpl implements ImageDetectorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleVisionServiceImpl.class);
	
	@Override
	public List<ImageDetailsModel> facialRecognition(String base64Image) throws ImageDetectorException {
		try {
			List<AnnotateImageRequest> requests = new ArrayList<>();
			requests.add(getImageRequest(base64Image, Feature.Type.FACE_DETECTION));
			return imageRecognitionExtraction(requests, Feature.Type.FACE_DETECTION);
		} catch (IOException e) {
			throw new ImageDetectorException(e.getMessage(), e);
		}
	}
	
	private List<ImageDetailsModel> imageRecognitionExtraction(List<AnnotateImageRequest> requests, Feature.Type featureType) throws ImageDetectorException {
		List<ImageDetailsModel> imageDetails = new ArrayList<ImageDetailsModel>();
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            for (AnnotateImageResponse res : response.getResponsesList()) {
                if (res.hasError()) {
                	LOGGER.error(res.getError().getMessage());
                	break;
                }
                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                	imageDetails.add(toImageDetailList(annotation));
                }
            }
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return imageDetails;
	}
	
	private ImageDetailsModel toImageDetailList(FaceAnnotation faceAnnotation) {
		ImageDetailsModel model = new ImageDetailsModel();
		model.setAngerLikelihood(faceAnnotation.getAngerLikelihoodValue());
		model.setBlurredLikelihood(faceAnnotation.getBlurredLikelihoodValue());
		model.setHeadwearLikelihood(faceAnnotation.getHeadwearLikelihoodValue());
		model.setJoyLikelihood(faceAnnotation.getJoyLikelihoodValue());
		model.setSorrowLikelihood(faceAnnotation.getSorrowLikelihoodValue());
		model.setSurpirseLikelihood(faceAnnotation.getSurpriseLikelihoodValue());
		model.setUnderExposeLikelihood(faceAnnotation.getUnderExposedLikelihoodValue());
		return model;
	}
	
	private AnnotateImageRequest getImageRequest(String base64Image, Feature.Type featType) throws IOException {
		ByteString byteString = ByteString.readFrom(decodeToImage(base64Image));
		Image image = Image.newBuilder().setContent(byteString).build();
		Feature feat = Feature.newBuilder().setType(featType).build();
		return AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(image).build();
	}
	
	private ByteArrayInputStream decodeToImage(String base64Image) {
		byte[] imageByte = Base64.decodeBase64(base64Image);
		return new ByteArrayInputStream(imageByte);
	}
	
//	public static void facialRecognition1(InputStream base64Image) throws IOException {
//		List<AnnotateImageRequest> requests = new ArrayList<>();
//		requests.add(getImageRequest(base64Image, Feature.Type.FACE_DETECTION));
//		
//		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
//            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//            for (AnnotateImageResponse res : responses) {
//                if (res.hasError()) {
//                    System.out.println("Error: " + res.getError().getMessage());
//                    return;
//                }
//
//                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
//                    System.out.println("Text: " + annotation.getDescription());
//                    System.out.println("Position : " + annotation.getBoundingPoly());
//                }
//            }
//		}
//	}
//	
//	public static void main(String[] args) throws IOException {
//		String imagePath = "D:\\Work\\Exercises\\project\\yelpOthers\\Image\\testImage.jpg";
//		File file = new File(imagePath);
//		byte[] imageByte = FileUtils.readFileToByteArray(file);
//		facialRecognition1(new ByteArrayInputStream(imageByte));
//		System.out.println("END");
//	}
	
}
