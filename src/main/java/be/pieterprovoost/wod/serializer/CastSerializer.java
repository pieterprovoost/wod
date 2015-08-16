package be.pieterprovoost.wod.serializer;

import be.pieterprovoost.wod.model.Cast;

public interface CastSerializer {

    String serialize(Cast cast, boolean pretty);

}
