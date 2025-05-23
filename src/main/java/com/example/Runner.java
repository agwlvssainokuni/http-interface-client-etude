/*
 * Copyright 2025 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.example.api.AnythingService;
import com.example.api.dto.AnythingRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AnythingService anythingService;

    public Runner(AnythingService anythingService) {
        this.anythingService = anythingService;
    }

    public void run(ApplicationArguments args) {

        logger.info("GET {}", anythingService.get(
                "ABCDE",
                "FGHIJ"
        ));

        logger.info("POST(form) {}", anythingService.postForm(
                "ABCDE",
                "FGHIJ"
        ));

        logger.info("POST(json) {}", anythingService.postJson(
                new AnythingRequestDto(
                        1001L,
                        "NAME",
                        List.of("ITEM1", "ITEM2", "ITEM3")
                )
        ));

        logger.info("async GET {}", anythingService.asyncGet(
                "ABCDE",
                "FGHIJ"
        ).block());

        logger.info("async POST(form) {}", anythingService.asyncPostForm(
                "ABCDE",
                "FGHIJ"
        ).block());

        logger.info("async POST(json) {}", anythingService.asyncPostJson(
                new AnythingRequestDto(
                        1001L,
                        "NAME",
                        List.of("ITEM1", "ITEM2", "ITEM3")
                )
        ).block());
    }

}
