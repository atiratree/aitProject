package ait.resource;

import ait.db.ConditionBuilder;
import ait.db.Managers;
import ait.db.Tables;
import ait.entity.Temperature;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by suomiy on 5/27/16.
 */
@Path("/temperature")
public class TemperatureResource {

    @Secured
    @POST
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> getTemperatures(TemperatureQueryDTO queryDTO) {
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(Tables.Temperature.YEAR, queryDTO.fromYear, conditionBuilder.GREATER_OR_EQUAL);
        conditionBuilder.where(Tables.Temperature.YEAR, queryDTO.toYear, conditionBuilder.SMALLER_OR_EQUAL);
        conditionBuilder.in(Tables.Temperature.MEASUREMENT_TYPE, queryDTO.measurmentTypes);
        conditionBuilder.in(Tables.Temperature.PLACE, queryDTO.places);
        return Managers.getTemperatureManager().find(conditionBuilder);
    }

    private static class TemperatureQueryDTO {
        private int fromYear;
        private int toYear;
        private List<Temperature.Place> places;
        private List<Temperature.MeasurmentType> measurmentTypes;

        public int getFromYear() {
            return fromYear;
        }

        public void setFromYear(int fromYear) {
            this.fromYear = fromYear;
        }

        public List<Temperature.MeasurmentType> getMeasurmentTypes() {
            return measurmentTypes;
        }

        public void setMeasurmentTypes(List<Temperature.MeasurmentType> measurmentTypes) {
            this.measurmentTypes = measurmentTypes;
        }

        public List<Temperature.Place> getPlaces() {
            return places;
        }

        public void setPlaces(List<Temperature.Place> places) {
            this.places = places;
        }

        public int getToYear() {
            return toYear;
        }

        public void setToYear(int toYear) {
            this.toYear = toYear;
        }
    }
}
