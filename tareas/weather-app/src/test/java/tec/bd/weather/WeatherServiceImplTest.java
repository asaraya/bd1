package tec.bd.weather;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherServiceImplTest {

    @Test
    public void GivenACity_WhenValidCity_ThenReturnTemperature() {
        // Arrange
        WeatherServiceImpl weatherService = new WeatherServiceImpl();

        // Act
        float actual = weatherService.getTemperature("Alajuela");

        // Assert
        assertThat(actual).isEqualTo(22.0f);
    }
}
