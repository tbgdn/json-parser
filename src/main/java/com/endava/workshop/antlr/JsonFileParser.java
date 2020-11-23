package com.endava.workshop.antlr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

@Slf4j
@RequiredArgsConstructor
public class JsonFileParser {

  private final String inputResourceFilePath;

  public Map<String, Object> parse() {
    log.info("Loading file to parse...");
    return Collections.emptyMap();
  }

  /*private CharStream createStream(String[] args) {
    String inputFilePath = null;
    if (args.length == 1) {
      inputFilePath = args[0];
    }
    InputStream in = System.in;
    if (inputFilePath == null) {
      log.warn("[WARNING] Reading input from System Input...");
    } else {
      in = this.getClass().getResourceAsStream(inputFilePath);
    }
    try {
      return CharStreams.fromStream(in);
    } catch (IOException e) {
      return CharStreams.fromString("");
    }
  }*/
}
