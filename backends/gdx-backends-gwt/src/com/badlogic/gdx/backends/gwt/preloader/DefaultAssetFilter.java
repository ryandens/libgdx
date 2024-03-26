/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.backends.gwt.preloader;

public class DefaultAssetFilter implements AssetFilter {
	private String extension (String file) {
		String name = file;
		int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) return "";
		return name.substring(dotIndex + 1);
	}

	@Override
	public boolean accept (String file, boolean isDirectory) {
		String normFile = file.replace('\\', '/');
		if (normFile.contains("/.")) return false;
		if (normFile.contains("/_")) return false;
		if (isDirectory && file.endsWith(".svn")) return false;
		return true;
	}

	@Override
	public boolean preload (String file) {
		return true;
	}

	@Override
	public AssetType getType (String file) {
		String extension = extension(file).toLowerCase();
		if (isImage(extension)) return AssetType.Image;
		if (isAudio(extension)) return AssetType.Audio;
		if (isText(extension)) return AssetType.Text;
		return AssetType.Binary;
	}

	private boolean isImage (String extension) {
		return "jpg".equals(extension) || "jpeg".equals(extension) || "png".equals(extension) || "bmp".equals(extension)
			|| "gif".equals(extension);
	}

	private boolean isText (String extension) {
		return "json".equals(extension) || "xml".equals(extension) || "txt".equals(extension) || "glsl".equals(extension)
			|| "fnt".equals(extension) || "pack".equals(extension) || "obj".equals(extension) || "atlas".equals(extension)
			|| "g3dj".equals(extension);
	}

	private boolean isAudio (String extension) {
		return "mp3".equals(extension) || "ogg".equals(extension) || "wav".equals(extension);
	}

	@Override
	public String getBundleName (String file) {
		return "assets";
	}
}
