package co.anbora.labs.engvid.api.dto;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.DEFAULT_IMAGE;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.SEARCH;

import co.anbora.labs.engvid.domain.constants.StremioConstants;

public interface Plugin {
  String ID = "co.anbora.labs.engvid.videos";
  String VERSION = "1.0.0";
  String NAME = "English Lessons Videos";
  String DESCRIPTION =
      "List of videos and courses for english learners: Beginners, Intermediate and Advanced lessons";
  String[] ID_PREFIXES =
      new String[] {StremioConstants.StremioCatalog.VIDEO_PREFIX_ID};
  String[] RESOURCES = new String[] {"catalog", "meta", "stream"};
  String[] TYPES = new String[] {"movie"};
  Catalog[] CATALOGS = new Catalog[] {
      new Catalog(StremioConstants.StremioCatalog.MOVIE,
                  StremioConstants.StremioCatalog.BEGINNER_ID_CATALOG,
                  "Beginner Lessons Catalog", new String[] {}, new String[0],
                  new String[] {SEARCH}),
      new Catalog(StremioConstants.StremioCatalog.MOVIE,
                  StremioConstants.StremioCatalog.INTERMEDIATE_ID_CATALOG,
                  "Intermediate Lessons Catalog", new String[] {},
                  new String[0], new String[] {SEARCH}),
      new Catalog(StremioConstants.StremioCatalog.MOVIE,
                  StremioConstants.StremioCatalog.ADVANCED_ID_CATALOG,
                  "Advanced Lessons Catalog", new String[] {}, new String[0],
                  new String[] {SEARCH})};
  String BACKGROUND = "";
  String LOGO = DEFAULT_IMAGE;
  String CONTACT_EMAIL = "anboralabs@gmail.com";
}
