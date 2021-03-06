/*
 * Copyright © 2015 Stefan Niederhauser (nidin@gmx.ch)
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
package guru.nidi.graphviz.attribute;

public interface Attributes {
    Attributes applyTo(MapAttributes attrs);

    default Attributes applyTo(Attributes attrs) {
        if (!(attrs instanceof MapAttributes)) {
            throw new UnsupportedOperationException("attributes must be a MapAttributes");
        }
        return applyTo((MapAttributes) attrs);
    }

    static Attributes attr(String key, Object value) {
        return new MapAttributes().add(key, value);
    }

    static Attributes attrs(Attributes... attributes) {
        final MapAttributes res = new MapAttributes();
        for (Attributes attribute : attributes) {
            attribute.applyTo(res);
        }
        return res;
    }

    default Object get(String key) {
        return applyTo(new MapAttributes()).get(key);
    }

    default boolean isEmpty() {
        return applyTo(new MapAttributes()).isEmpty();
    }
}
