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

package com.example.api;

import com.example.api.dto.AnythingRequestDto;
import com.example.api.dto.AnythingResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/anything")
public interface AnythingService {

    @GetExchange
    ResponseEntity<AnythingResponseDto> get(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2
    );

    @PostExchange(
            contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    ResponseEntity<AnythingResponseDto> postForm(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2
    );

    @PostExchange
    ResponseEntity<AnythingResponseDto> postJson(
            @RequestBody AnythingRequestDto anythingRequestDto
    );

}
