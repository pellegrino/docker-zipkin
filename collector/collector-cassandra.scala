/*
 * Copyright 2012 Twitter Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.twitter.zipkin.builder.Scribe
import com.twitter.zipkin.cassandra
import com.twitter.zipkin.collector.builder.CollectorServiceBuilder
import com.twitter.zipkin.storage.Store
import com.twitter.logging.LoggerFactory
import com.twitter.logging.LoggerFactory
import com.twitter.logging.Level
import com.twitter.logging.ConsoleHandler
import com.twitter.zipkin.builder.ZipkinServerBuilder

val keyspaceBuilder = cassandra.Keyspace.static(nodes = Set("localhost"))
val cassandraBuilder = Store.Builder(
  cassandra.StorageBuilder(keyspaceBuilder),
  cassandra.IndexBuilder(keyspaceBuilder),
  cassandra.AggregatesBuilder(keyspaceBuilder)
)


val loggerFactory = new LoggerFactory(
            node = "",
            level = Some(Level.INFO),
            handlers = List(ConsoleHandler())
            )


CollectorServiceBuilder(Scribe.Interface(categories = Set("zipkin")))
  .writeTo(cassandraBuilder)
  .copy(serverBuilder = ZipkinServerBuilder(9410, 9900).loggers(List(loggerFactory))) 
