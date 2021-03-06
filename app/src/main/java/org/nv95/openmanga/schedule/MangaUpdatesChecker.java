package org.nv95.openmanga.schedule;

import android.content.Context;
import android.support.annotation.WorkerThread;

import org.nv95.openmanga.core.models.MangaDetails;
import org.nv95.openmanga.core.models.MangaHeader;
import org.nv95.openmanga.core.providers.MangaProvider;

/**
 * Created by koitharu on 30.01.18.
 */

public final class MangaUpdatesChecker {

	private final Context mContext;

	public MangaUpdatesChecker(Context context) {
		mContext = context;
	}

	@WorkerThread
	public int getChaptersCount(MangaHeader manga) {
		try {
			final MangaProvider provider = MangaProvider.get(mContext, manga.provider);
			final MangaDetails details = provider.getDetails(manga);
			return details.chapters.size();
		} catch (Exception e) {
			return -1;
		}
	}
}
