package ait.resource;

import ait.db.ConditionBuilder;
import ait.db.Managers;
import ait.db.Tables;
import ait.entity.CartType;
import ait.entity.Temperature;
import ait.servlet.utils.LoginUtils;
import ait.utils.TemperatureMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
    public List<Temperature> getTemperatures(TemperatureQueryDTO queryDTO, @Context HttpServletRequest request) {
        LoginUtils.checkAuthorization(queryDTO.getType(), request);

        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(Tables.Temperature.YEAR, queryDTO.fromYear, conditionBuilder.GREATER_OR_EQUAL);
        conditionBuilder.where(Tables.Temperature.YEAR, queryDTO.toYear, conditionBuilder.SMALLER_OR_EQUAL);
        conditionBuilder.in(Tables.Temperature.MEASUREMENT_TYPE, TemperatureMapper.getMeasurementTypes(queryDTO.type));
        conditionBuilder.in(Tables.Temperature.PLACE, TemperatureMapper.getPlaces(queryDTO.type));
        return Managers.getTemperatureManager().find(conditionBuilder);
    }

    private static class TemperatureQueryDTO {
        private int fromYear;
        private int toYear;
        private CartType type;

        public int getFromYear() {
            return fromYear;
        }

        public void setFromYear(int fromYear) {
            this.fromYear = fromYear;
        }

        public int getToYear() {
            return toYear;
        }

        public void setToYear(int toYear) {
            this.toYear = toYear;
        }

        public CartType getType() {
            return type;
        }

        public void setType(CartType type) {
            this.type = type;
        }
    }
}

