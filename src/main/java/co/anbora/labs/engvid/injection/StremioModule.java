package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.constants.StremioConstants.Plugin;
import co.anbora.labs.engvid.domain.model.stremio.Manifest;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Dependent
public class StremioModule {

    @Produces
    Manifest provideManifest() {
        return new Manifest(
                Plugin.ID,
                Plugin.VERSION,
                Plugin.NAME,
                Plugin.DESCRIPTION,
                Plugin.ID_PREFIXES,
                Plugin.RESOURCES,
                Plugin.TYPES,
                Plugin.CATALOGS,
                Plugin.BACKGROUND,
                Plugin.LOGO,
                Plugin.CONTACT_EMAIL
        );
    }

}
