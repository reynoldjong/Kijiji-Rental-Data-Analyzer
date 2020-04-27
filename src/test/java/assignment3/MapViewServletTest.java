package assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;

public class MapViewServletTest {

    @Test
    public void testMapViewServlet() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        try {
            when(resp.getWriter()).thenReturn(writer);
        } catch (IOException e1) {
            fail();
        }

        MapViewServlet testServlet = new MapViewServlet();
        testServlet.init();

        try {
            testServlet.doGet(req, resp);
            // verify we have the correct json format
            // content does not matter as much
            String reply = stringWriter.toString();
            assertEquals("{\"", reply.subSequence(0, 2));
            assertEquals(" }", reply.subSequence(reply.length() - 2, reply.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
