package org.personal.mason.feop.oauth.service.spi;

import java.io.InputStream;
import java.util.Collection;

public interface PhotoService {
	/**
	 * Load the photos for the current user.
	 * 
	 * @return The photos for the current user.
	 */
	Collection<PhotoInfo> getPhotosForCurrentUser(String username);

	/**
	 * Load a photo by id.
	 * 
	 * @param id
	 *            The id of the photo.
	 * @return The photo that was read.
	 */
	InputStream loadPhoto(String id);
}
