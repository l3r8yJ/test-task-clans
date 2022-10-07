package org.l3r8y;

import static java.net.URLDecoder.decode;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Application {
  public static void main(final String[] args) throws IOException {
    final int port = 8080;
    final HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext(
        "/clans",
        (exchange -> {
          final Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getQuery());
          final String noNameText = "Anonymous";
          final String name =
              params.getOrDefault("name", List.of(noNameText)).stream()
                  .findFirst()
                  .orElse(noNameText);
          final String respText = String.format("Hello %s!", name);
          exchange.sendResponseHeaders(200, respText.getBytes().length);
          final OutputStream output = exchange.getResponseBody();
          output.write(respText.getBytes());
          output.flush();
          exchange.close();
        }));
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  public static Map<String, List<String>> splitQuery(final String query) {
    if (null == query || "".equals(query)) {
      return Collections.emptyMap();
    }
    return Pattern.compile("&")
        .splitAsStream(query)
        .map(s -> Arrays.copyOf(s.split("="), 2))
        .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));
  }
}
