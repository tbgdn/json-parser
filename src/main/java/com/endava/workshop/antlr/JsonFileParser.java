package com.endava.workshop.antlr;

import com.endava.workshop.JsonLexer;
import com.endava.workshop.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

@Slf4j
@RequiredArgsConstructor
public class JsonFileParser {

  private final String inputResourceFilePath;

  public Map<String, Object> parse() {
    /*log.info("Loading file to parse...");
    CharStream stream = createStream(new String[]{inputResourceFilePath});
    JsonLexer jsonLexer = new JsonLexer(stream);
    CommonTokenStream tokens = new CommonTokenStream(jsonLexer);
    JsonParser parser = new JsonParser(tokens);
    log.info("Parse the loaded file...");
    ParseTree parseTree = parser.obj();
    ParseTreeWalker walker = new ParseTreeWalker();
    log.info("Process the loaded file...");
    JsonValueGenerator valueGenerator = new JsonValueGenerator();
    walker.walk(valueGenerator, parseTree);*/
    return Collections.emptyMap();
  }

  private CharStream createStream(String[] args) {
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
  }
}
