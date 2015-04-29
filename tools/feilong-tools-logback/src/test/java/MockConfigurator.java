import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class MockConfigurator extends JoranConfigurator implements Configurator{

    @Override
    public void configure(LoggerContext loggerContext){
        String url = getUrl();
        try{
            doConfigure(url);
        }catch (JoranException e){
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @since 1.1.2
     */
    private String getUrl(){

        //TODO
        String url = "";
        //ResourceUtils.getFile("");
        return url;
    }
}