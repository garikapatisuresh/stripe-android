package com.stripe.android.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static com.stripe.android.model.SourceCodeVerificationTest.EXAMPLE_JSON_CODE_VERIFICATION;
import static com.stripe.android.model.SourceOwnerTest.EXAMPLE_JSON_OWNER_WITHOUT_NULLS;
import static com.stripe.android.model.SourceOwnerTest.EXAMPLE_MAP_OWNER;
import static com.stripe.android.model.SourceReceiverTest.EXAMPLE_MAP_RECEIVER;
import static com.stripe.android.model.SourceRedirectTest.EXAMPLE_JSON_REDIRECT;

import static com.stripe.android.testharness.JsonTestUtils.assertJsonEquals;
import static com.stripe.android.testharness.JsonTestUtils.assertMapEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Test class for {@link Source} model.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 23)
public class SourceTest {

    private static final String EXAMPLE_JSON_SOURCE_WITH_NULLS = "{\n"+
            "\"id\": \"src_19t3xKBZqEXluyI4uz2dxAfQ\",\n"+
            "\"object\": \"source\",\n"+
            "\"amount\": 1000,\n"+
            "\"client_secret\": \"src_client_secret_of43INi1HteJwXVe3djAUosN\",\n"+
            "\"created\": 1488499654,\n"+
            "\"currency\": \"usd\",\n"+
            "\"flow\": \"receiver\",\n"+
            "\"livemode\": false,\n"+
            "\"metadata\": {\n"+
            "},\n"+
            "\"owner\": {\n"+
            "\"address\": null,\n"+
            "\"email\": \"jenny.rosen@example.com\",\n"+
            "\"name\": \"Jenny Rosen\",\n"+
            "\"phone\": \"4158675309\",\n"+
            "\"verified_address\": null,\n"+
            "\"verified_email\": null,\n"+
            "\"verified_name\": null,\n"+
            "\"verified_phone\": null\n"+
            "},\n"+
            "\"receiver\": {\n"+
            "\"address\": \"test_1MBhWS3uv4ynCfQXF3xQjJkzFPukr4K56N\",\n"+
            "\"amount_charged\": 0,\n"+
            "\"amount_received\": 0,\n"+
            "\"amount_returned\": 0\n"+
            "},\n"+
            "\"status\": \"pending\",\n"+
            "\"type\": \"bitcoin\",\n"+
            "\"usage\": \"single_use\"\n"+
            "}";

    private static final Map<String, Object> EXAMPLE_SOURCE_MAP = new HashMap<String, Object>() {{
        put("id", "src_19t3xKBZqEXluyI4uz2dxAfQ");
        put("amount", 1000L);
        put("client_secret", "src_client_secret_of43INi1HteJwXVe3djAUosN");
        put("created", 1488499654L);
        put("currency", "usd");
        put("flow", "receiver");
        put("livemode", false);
        put("metadata", new HashMap<String, Object>());
        put("owner", EXAMPLE_MAP_OWNER);
        put("receiver", EXAMPLE_MAP_RECEIVER);
        put("status", "pending");
        put("type", "bitcoin");
        put("usage", "single_use");
    }};

    private static final String EXAMPLE_JSON_SOURCE_WITHOUT_NULLS = "{\n"+
            "\"id\": \"src_19t3xKBZqEXluyI4uz2dxAfQ\",\n"+
            "\"object\": \"source\",\n"+
            "\"amount\": 1000,\n"+
            "\"client_secret\": \"src_client_secret_of43INi1HteJwXVe3djAUosN\",\n"+
            "\"code_verification\": " + EXAMPLE_JSON_CODE_VERIFICATION + ",\n"+
            "\"created\": 1488499654,\n"+
            "\"currency\": \"usd\",\n"+
            "\"flow\": \"receiver\",\n"+
            "\"livemode\": false,\n"+
            "\"metadata\": {\n"+
            "},\n"+
            "\"owner\": " + EXAMPLE_JSON_OWNER_WITHOUT_NULLS +",\n"+
            "\"redirect\": " + EXAMPLE_JSON_REDIRECT + ",\n"+
            "\"receiver\": {\n"+
            "\"address\": \"test_1MBhWS3uv4ynCfQXF3xQjJkzFPukr4K56N\",\n"+
            "\"amount_charged\": 0,\n"+
            "\"amount_received\": 0,\n"+
            "\"amount_returned\": 0\n"+
            "},\n"+
            "\"status\": \"pending\",\n"+
            "\"type\": \"bitcoin\",\n"+
            "\"usage\": \"single_use\",\n"+
            "\"bitcoin\": {\n" +
            "\"address\": \"test_1MBhWS3uv4ynCfQXF3xQjJkzFPukr4K56N\",\n" +
            "\"amount\": 2371000,\n" +
            "\"amount_charged\": 0,\n" +
            "\"amount_received\": 0,\n" +
            "\"amount_returned\": 0,\n" +
            "\"uri\": \"bitcoin:test_1MBhWS3uv4ynCfQXF3xQjJkzFPukr4K56N?amount=0.02371000\"\n" +
            "}" +
            "}";

    private Source mSource;

    @Before
    public void setup() {
        mSource = Source.fromString(EXAMPLE_JSON_SOURCE_WITHOUT_NULLS);
        assertNotNull(mSource);
    }

    @Test
    public void fromJsonString_backToJson_createsIdenticalElement() {
        try {
            JSONObject rawConversion = new JSONObject(EXAMPLE_JSON_SOURCE_WITHOUT_NULLS);
            JSONObject actualObject = mSource.toJson();
            assertJsonEquals(rawConversion, actualObject);
        } catch (JSONException jsonException) {
            fail("Test Data failure: " + jsonException.getLocalizedMessage());
        }
    }

    @Test
    public void fromJsonStringWithNulls_toMap_createsExpectedMap() {
        Source sourceWithNulls = Source.fromString(EXAMPLE_JSON_SOURCE_WITH_NULLS);
        assertNotNull("Test Data failure during parsing", sourceWithNulls);
        assertMapEquals(EXAMPLE_SOURCE_MAP, sourceWithNulls.toMap());
    }

}
