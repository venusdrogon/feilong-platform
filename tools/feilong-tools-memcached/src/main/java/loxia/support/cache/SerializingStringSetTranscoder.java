package loxia.support.cache;

import java.util.Set;

import net.spy.memcached.CachedData;
import net.spy.memcached.transcoders.BaseSerializingTranscoder;
import net.spy.memcached.transcoders.Transcoder;

public class SerializingStringSetTranscoder extends BaseSerializingTranscoder implements Transcoder<Set<String>>{

	// General flags
	static final int	SERIALIZED			= 1;

	static final int	COMPRESSED			= 2;

	// Special flags for specially handled types.
	static final int	SPECIAL_BOOLEAN		= (1 << 8);

	static final int	SPECIAL_INT			= (2 << 8);

	static final int	SPECIAL_LONG		= (3 << 8);

	static final int	SPECIAL_DATE		= (4 << 8);

	static final int	SPECIAL_BYTE		= (5 << 8);

	static final int	SPECIAL_FLOAT		= (6 << 8);

	static final int	SPECIAL_DOUBLE		= (7 << 8);

	static final int	SPECIAL_BYTEARRAY	= (8 << 8);

	/**
	 * Get a serializing transcoder with the default max data size.
	 */
	public SerializingStringSetTranscoder(){
		this(CachedData.MAX_SIZE);
	}

	/**
	 * Get a serializing transcoder that specifies the max data size.
	 */
	public SerializingStringSetTranscoder(int max){
		super(max);
	}

	@Override
	public boolean asyncDecode(CachedData d){
		if ((d.getFlags() & COMPRESSED) != 0 || (d.getFlags() & SERIALIZED) != 0){
			return true;
		}
		return super.asyncDecode(d);
	}

	/*
	 * (non-Javadoc)
	 * @see net.spy.memcached.Transcoder#decode(net.spy.memcached.CachedData)
	 */
	public Set<String> decode(CachedData d){
		byte[] data = d.getData();
		Set<String> rv = null;
		if ((d.getFlags() & COMPRESSED) != 0){
			data = decompress(d.getData());
		}
		return (Set<String>) deserialize(data);
	}

	/*
	 * (non-Javadoc)
	 * @see net.spy.memcached.Transcoder#encode(java.lang.Object)
	 */
	public CachedData encode(Set<String> strSet){
		byte[] b = null;
		int flags = 0;
		b = serialize(strSet);
		flags |= SERIALIZED;
		assert b != null;
		if (b.length > compressionThreshold){
			byte[] compressed = compress(b);
			if (compressed.length < b.length){
				getLogger().debug("Compressed %s from %d to %d", Set.class, b.length, compressed.length);
				b = compressed;
				flags |= COMPRESSED;
			}else{
				getLogger().info("Compression increased the size of %s from %d to %d", Set.class, b.length, compressed.length);
			}
		}
		return new CachedData(flags, b, getMaxSize());
	}
}
