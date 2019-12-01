/*
 * Copyright 2019 Google LLC
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

package com.google.android.gnd.persistence.local.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.util.List;

@Dao
public interface ProjectDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Completable insertOrUpdate(ProjectEntity project);

  @Delete
  Completable deleteProject(ProjectEntity projectEntity);

  @Query("SELECT * FROM project WHERE id = :id")
  Maybe<ProjectEntity> findById(String id);

  @Query("SELECT * FROM project")
  Maybe<List<ProjectEntity>> findAll();

  @Query("SELECT * FROM project WHERE is_active = 1 LIMIT 1")
  Maybe<ProjectEntity> getLastActiveProject();
}
