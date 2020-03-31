package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.api.Manifest;
import co.anbora.labs.engvid.api.manifest.Plugin;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class StremioModule {

    @Produces
    public Manifest provideManifest() {
        return new Manifest(
                new Plugin.Version(
                        Plugin.ID,
                        Plugin.VERSION,
                        Plugin.NAME,
                        Plugin.DESCRIPTION
                ),
                new Plugin.Resources(
                        Plugin.ID_PREFIXES,
                        Plugin.RESOURCES,
                        Plugin.TYPES,
                        Plugin.CATALOGS
                ),
                new Plugin.Contact(
                        Plugin.BACKGROUND,
                        Plugin.LOGO,
                        Plugin.CONTACT_EMAIL
                )
        );
    }

}
