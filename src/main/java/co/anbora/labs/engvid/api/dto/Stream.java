package co.anbora.labs.engvid.api.dto;

import co.anbora.labs.engvid.domain.model.Lesson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Stream {

  @JsonProperty("streams")
  private List<StreamItem> streams = new LinkedList<>();

  public List<StreamItem> getStreams() { return streams; }

  public Stream addStream(String url) {
    streams.add(new StreamItem(url));
    return this;
  }

  public static Stream from(Lesson lesson) {
    return new Stream().addStream(lesson.getYoutubeId());
  }
}
