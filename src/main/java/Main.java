import app.Api;
import app.Servlet;
import app.Web;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.ShapeRasterizer;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.PictureWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class,
        MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, DispatcherServletAutoConfiguration.class})
@ComponentScan(basePackages = {"configuration"})
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(banner());
        start(Main.class, 8080).run(args);
    }
    
    private static SpringApplication start(Class<?> parent, int port) {
        return new SpringApplicationBuilder(parent)
                .bannerMode(Banner.Mode.OFF)
                .properties("app.port=${other.port:" + port + "}").build();
    }

    @Bean
    public ServletRegistrationBean apiApp() {
        return Servlet.createServlet("api", Api.class, "/api/*");
    }

    @Bean
    public ServletRegistrationBean webApp() {
        return Servlet.createServlet("web", Web.class, "/");
    }

    private static String banner() throws IOException {
        File image = new File(Main.class.getResource("/banner.png")
                .toString().replace("file:", ""));
        BufferedImage circleImage = ImageIO.read(image);
        TextWidget widget = new PictureWidget(new TextBoxSize(80, 30),
                circleImage, new ShapeRasterizer());
        ExtensibleCharacterRaster raster = new ExtensibleCharacterRaster();
        widget.render(new RasterContext(raster));
        return String.valueOf(raster);
    }
}
