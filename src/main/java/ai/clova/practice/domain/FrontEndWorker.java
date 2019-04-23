package ai.clova.practice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "frontWorker")
public class FrontEndWorker extends Worker {
}
