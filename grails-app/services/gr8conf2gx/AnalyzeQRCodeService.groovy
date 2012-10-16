package gr8conf2gx

import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.multi.GenericMultipleBarcodeReader

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import com.google.zxing.*

class AnalyzeQRCodeService {

	Result[] decodeMulti(def input, Map<DecodeHintType, ?> hints = [:]) throws IOException {
		BufferedImage image;
		try {
			image = ImageIO.read(input);
		} catch(IllegalArgumentException iae) {
			throw new FileNotFoundException("Resource not found");
		}
		if(image == null) {
			System.err.println("Could not load image");
			return [];
		}
		try {
			LuminanceSource source;
			source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			MultiFormatReader multiFormatReader = new MultiFormatReader();
			GenericMultipleBarcodeReader reader = new GenericMultipleBarcodeReader(
					multiFormatReader);
			return reader.decodeMultiple(bitmap, hints);
		} catch(NotFoundException nfe) {
			System.out.println("No barcode found");
			return [];
		}
	}
}
