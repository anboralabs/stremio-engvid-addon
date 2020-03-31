package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.api.dto.Manifest;
import co.anbora.labs.engvid.api.dto.Plugin;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class StremioModule {

    @Produces
    public Manifest provideManifest() {
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
