package com.munish.lms.libraryassets;

public interface Asset {
	public assetType getAssetType();
	public enum assetType
	{
		BOOK,
		CD,
		DVD,
		VHS,
		NONE
	}
}
