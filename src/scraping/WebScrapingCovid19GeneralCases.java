package scraping;

import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author javier
 */
public class WebScrapingCovid19GeneralCases {

    private final String url;
    private final int timeout;
    private final String userAgent;
    private final String msgError = "Error crítico. Posible error de conexión al "
            + "servidor objetivo";

    public WebScrapingCovid19GeneralCases(String url, int timeout, String userAgent) {
        this.url = url;
        this.timeout = timeout;
        this.userAgent = userAgent;
    }

    /**
     * Intenta una conexión con el servidor objetivo y devuelve el estado de la
     * conexión.
     *
     * @return código de estado de la solicitud.
     * @throws IOException Si hay algún error en la conexión u otro problema
     */
    public int getStatusCode() throws IOException {
        try {
            Response response = Jsoup.connect(this.url)
                    .userAgent(this.userAgent)
                    .timeout(this.timeout)
                    .ignoreHttpErrors(true)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml"
                            + ";q=0.9,image/avif,image/webp,image"
                            + "/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .execute();
            return response.statusCode();
        } catch (IOException ex) {
            throw new IOException(this.msgError);
        }
    }

    /**
     * Intenta realizar una conexión al servidor objetivo para devolver el
     * contenido de la página en la url proporcionada en el constructor
     *
     * @return Objeto Document con el contenido html del objetivo.
     * @throws IOException Si hay algún problema con la conexión al servidor
     * objetivo
     */
    public Document getHtmlDocument() throws IOException {
        try {
            Document doc = Jsoup.connect(this.url)
                    .userAgent(this.userAgent)
                    .timeout(this.timeout)
                    .ignoreHttpErrors(true)
                    .get();
            return doc;
        } catch (IOException ex) {
            throw new IOException(this.msgError);
        }
    }
}