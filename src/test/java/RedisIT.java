import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by andreas.maier on 04.05.17.
 */
public class RedisIT {

    private RedisServer redisServer;

    @Before
    public void setUp() throws IOException {

        redisServer = new RedisServer();
        redisServer.start();
    }

    @Test
    public void test() {

        Jedis jedis = new Jedis("localhost");
        jedis.set("foo1", "bar1");
        jedis.set("foo1", "bar1_new");
        jedis.set("foo2", "bar2");
        assertEquals("bar1_new", jedis.get("foo1"));
        assertEquals("bar2", jedis.get("foo2"));
        assertNull(jedis.get("foo"));
    }

    @After
    public void tearDown() {
        redisServer.stop();
    }
}
