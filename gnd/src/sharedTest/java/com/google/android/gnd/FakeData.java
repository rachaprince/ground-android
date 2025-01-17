/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gnd;

import com.google.android.gnd.model.AuditInfo;
import com.google.android.gnd.model.Project;
import com.google.android.gnd.model.TermsOfService;
import com.google.android.gnd.model.User;
import com.google.android.gnd.model.feature.GeoJsonFeature;
import com.google.android.gnd.model.feature.Point;
import com.google.android.gnd.model.feature.PointFeature;
import com.google.android.gnd.model.feature.PolygonFeature;
import com.google.android.gnd.model.layer.Layer;
import com.google.android.gnd.model.layer.Layer.Builder;
import com.google.android.gnd.model.layer.Style;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Shared test data constants. Tests are expected to override existing or set missing values when
 * the specific value is relevant to the test.
 */
public class FakeData {
  // TODO: Replace constants with calls to newFoo() methods.
  public static final TermsOfService TERMS_OF_SERVICE =
      TermsOfService.builder()
          .setId("TERMS_OF_SERVICE")
          .setText("Fake Terms of Service text")
          .build();

  public static final Layer LAYER = newLayer().build();

  public static Builder newLayer() {
    return Layer.newBuilder()
        .setId("LAYER")
        .setName("Layer")
        .setDefaultStyle(Style.builder().setColor("#00ff00").build());
  }

  public static final User USER =
      User.builder().setId("user_id").setEmail("user@gmail.com").setDisplayName("User").build();

  public static final User USER_2 =
      User.builder().setId("user_id_2").setEmail("user2@gmail.com").setDisplayName("User2").build();

  public static final Project PROJECT = newProject().build();

  public static Project.Builder newProject() {
    return Project.newBuilder()
        .setId("PROJECT")
        .setTitle("Project title")
        .setDescription("Test project description")
        .setAcl(ImmutableMap.of(FakeData.USER.getEmail(), "contributor"));
  }

  public static final PointFeature POINT_FEATURE =
      PointFeature.newBuilder()
          .setId("feature id")
          .setProject(PROJECT)
          .setLayer(LAYER)
          .setPoint(Point.newBuilder().setLatitude(0.0).setLongitude(0.0).build())
          .setCreated(AuditInfo.now(USER))
          .setLastModified(AuditInfo.now(USER))
          .build();

  public static final ImmutableList<Point> VERTICES =
      ImmutableList.of(
          Point.newBuilder().setLatitude(0.0).setLongitude(0.0).build(),
          Point.newBuilder().setLatitude(10.0).setLongitude(10.0).build(),
          Point.newBuilder().setLatitude(20.0).setLongitude(20.0).build());

  public static final PolygonFeature POLYGON_FEATURE =
      PolygonFeature.builder()
          .setId("feature id")
          .setProject(PROJECT)
          .setLayer(LAYER)
          .setVertices(VERTICES)
          .setCreated(AuditInfo.now(USER))
          .setLastModified(AuditInfo.now(USER))
          .build();

  public static final GeoJsonFeature GEO_JSON_FEATURE =
      GeoJsonFeature.newBuilder()
          .setId("feature id")
          .setProject(PROJECT)
          .setLayer(LAYER)
          .setGeoJsonString("some data string")
          .setCreated(AuditInfo.now(USER))
          .setLastModified(AuditInfo.now(USER))
          .build();

  public static final Point POINT = Point.newBuilder().setLatitude(42.0).setLongitude(18.0).build();
}
