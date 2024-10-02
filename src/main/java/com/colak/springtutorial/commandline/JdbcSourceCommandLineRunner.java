package com.colak.springtutorial.commandline;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.JetService;
import com.hazelcast.jet.Job;
import com.hazelcast.jet.pipeline.BatchSource;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
@RequiredArgsConstructor
public class JdbcSourceCommandLineRunner implements CommandLineRunner {

    private final HazelcastInstance hazelcastInstance;

    @Override
    public void run(String... args) {
        BatchSource<String> source = Sources.jdbc(
                new NewConnectionProvider(),
                (con, parallelism, index) -> {
                    PreparedStatement stmt = con.prepareStatement("SELECT name FROM cities");
                    return stmt.executeQuery();
                },
                resultSet -> resultSet.getString(1)
        );

        Pipeline pipeline = Pipeline.create();
        pipeline.readFrom(source)
                .writeTo(Sinks.logger());

        JetService jetService = hazelcastInstance.getJet();
        Job job = jetService.newJob(pipeline);
        job.join();
    }
}
