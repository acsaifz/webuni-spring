package hu.webuni.hr.acsaifz.web;

import hu.webuni.hr.acsaifz.AbstractIntegrationTest;
import hu.webuni.hr.acsaifz.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeControllerIT extends AbstractIntegrationTest {
    private final static String BASE_URL = "/api/employees";

    private EmployeeDto employeeDto;

    @BeforeEach
    void init(){
        employeeDto = new EmployeeDto("Skywalker Lajos", "Jedi", 1_500_000,
                LocalDate.of(2000, 5,17));
    }

    @Test
    void itShouldCreateEmployeeAndListedIt(){
        //GIVEN
        List<EmployeeDto> employeesBefore = getAllEmployees();

        //WHEN
        createDummyEmployee();
        List<EmployeeDto> employeesAfter = getAllEmployees();

        //THEN
        EmployeeDto actualEmployeeDto = employeesAfter.get(employeesAfter.size() - 1);

        assertThat(employeesAfter.subList(0, employeesBefore.size()))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(employeesBefore);

        assertThat(actualEmployeeDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(employeeDto);
    }

    @Test
    void createEmployeeShouldResponseBadRequestWhenNameIsBlank(){
        //GIVEN
        employeeDto.setName("");

        //WHEN
        ResponseSpec actualResponse = doPostWithBody(BASE_URL, employeeDto);

        //THEN
        actualResponse.expectStatus().isBadRequest();
    }

    @Test
    void createEmployeeShouldResponseBadRequestWhenJobTitleIsBlank(){
        //GIVEN
        employeeDto.setJobTitle("");

        //WHEN
        ResponseSpec actualResponse = doPostWithBody(BASE_URL, employeeDto);

        //THEN
        actualResponse.expectStatus().isBadRequest();
    }

    @Test
    void createEmployeeShouldResponseBadRequestWhenMonthlySalaryIsNotPositive(){
        //GIVEN
        EmployeeDto employeeDtoWithZeroSalary = new EmployeeDto("Skywalker Lajos", "Jedi",
                0, LocalDate.of(2000, 5,17));
        EmployeeDto employeeDtoWithNegativeSalary = new EmployeeDto("Skywalker Lajos", "Jedi",
                -1_500_000, LocalDate.of(2000, 5,17));

        //WHEN
        ResponseSpec actualResponseZeroSalary = doPostWithBody(BASE_URL, employeeDtoWithZeroSalary);
        ResponseSpec actualResponseNegativeSalary = doPostWithBody(BASE_URL, employeeDtoWithNegativeSalary);

        //THEN
        actualResponseZeroSalary.expectStatus().isBadRequest();
        actualResponseNegativeSalary.expectStatus().isBadRequest();
    }

    @Test
    void createEmployeeShouldResponseBadRequestWhenEntryDateIsInFuture(){
        //GIVEN
        employeeDto.setEntryDate(LocalDate.now().plusDays(1));

        //WHEN
        ResponseSpec actualResponse = doPostWithBody(BASE_URL, employeeDto);

        //THEN
        actualResponse.expectStatus().isBadRequest();
    }

    @Test
    void getEmployeeShouldResponseOkAndReturnIt(){
        //GIVEN
        EmployeeDto expectedEmployeeDto = createDummyEmployee();

        //WHEN
        ResponseSpec actualResponse = doGet(BASE_URL + "/" + expectedEmployeeDto.getId());

        //THEN
        EmployeeDto actualEmployeeDto = actualResponse.expectStatus().isOk().expectBody(EmployeeDto.class)
                .returnResult().getResponseBody();
        assertThat(actualEmployeeDto).usingRecursiveComparison().isEqualTo(expectedEmployeeDto);
    }

    @Test
    void getEmployeeShouldResponseNotFoundWhenNotExist() {
        //GIVEN

        //WHEN
        ResponseSpec actualResponse = doGet(BASE_URL + "/" + Long.MAX_VALUE);

        //THEN
        actualResponse.expectStatus().isNotFound();
    }

    @Test
    void modifyEmployeeShouldResponseOkAndReturnIt() {
        //GIVEN
        EmployeeDto expectedEmployeeDto = createDummyEmployee();

        expectedEmployeeDto.setName("Mekk Elek");
        expectedEmployeeDto.setJobTitle("Handyman");
        expectedEmployeeDto.setMonthlySalary(1_000_000);
        expectedEmployeeDto.setEntryDate(LocalDate.of(1974, 11, 30));

        //WHEN
        ResponseSpec actualResponse = doPutWithBody(BASE_URL + "/" + expectedEmployeeDto.getId(),
                expectedEmployeeDto);

        //THEN
        EmployeeDto actualEmployeeDto = actualResponse.expectStatus().isOk().expectBody(EmployeeDto.class)
                .returnResult().getResponseBody();
        assertThat(actualEmployeeDto).usingRecursiveComparison().isEqualTo(expectedEmployeeDto);
    }

    @Test
    void modifyEmployeeShouldResponseNotFoundWhenNotExist() {
        //GIVEN

        //WHEN
        ResponseSpec actualResponse = doPutWithBody(BASE_URL + "/" + Long.MAX_VALUE, employeeDto);

        //THEN
        actualResponse.expectStatus().isNotFound();
    }

    @Test
    void modifyEmployeeShouldResponseBadRequestWhenNotValid(){
        //GIVEN
        EmployeeDto employeeDtoWithBlankName = createDummyEmployee();
        employeeDtoWithBlankName.setName("");

        EmployeeDto employeeDtoWithBlankJobTitle = createDummyEmployee();
        employeeDtoWithBlankJobTitle.setJobTitle("");

        EmployeeDto employeeDtoWithNegativeSalary = createDummyEmployee();
        employeeDtoWithNegativeSalary.setMonthlySalary(-1_500_000);

        EmployeeDto employeeDtoWithEntryDateInFuture = createDummyEmployee();
        employeeDtoWithEntryDateInFuture.setEntryDate(LocalDate.now().plusDays(1));

        //WHEN
        ResponseSpec actualResponseWithBlankName = doPutWithBody(BASE_URL + "/" +
                employeeDtoWithBlankName.getId(), employeeDtoWithBlankName);
        ResponseSpec actualResponseWithBlankJobTitle = doPutWithBody(BASE_URL + "/" +
                employeeDtoWithBlankJobTitle.getId(), employeeDtoWithBlankJobTitle);
        ResponseSpec actualResponseWithNegativeSalary = doPutWithBody(BASE_URL + "/" +
                employeeDtoWithNegativeSalary.getId(), employeeDtoWithNegativeSalary);
        ResponseSpec actualResponseWithEntryDateInFuture = doPutWithBody(BASE_URL + "/" +
                employeeDtoWithEntryDateInFuture.getId(), employeeDtoWithEntryDateInFuture);

        //THEN
        actualResponseWithBlankName.expectStatus().isBadRequest();
        actualResponseWithBlankJobTitle.expectStatus().isBadRequest();
        actualResponseWithNegativeSalary.expectStatus().isBadRequest();
        actualResponseWithEntryDateInFuture.expectStatus().isBadRequest();
    }

    @Test
    void deleteEmployeeShouldResponseNotContentAndDelistedIt() {
        //GIVEN
        EmployeeDto employeeDto = createDummyEmployee();
        List<EmployeeDto> employeesBefore = getAllEmployees();

        //WHEN
        ResponseSpec actualResponse = doDelete(BASE_URL + "/" + employeeDto.getId());
        List<EmployeeDto> employeesAfter = getAllEmployees();

        //THEN
        actualResponse.expectStatus().isNoContent();
        assertThat(employeesAfter).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(employeesBefore.subList(0, employeesAfter.size()))
                .doesNotContain(employeeDto);
    }

    @Test
    void deleteEmployeeShouldResponseNotFoundWhenNotExist(){
        //GIVEN

        //WHEN
        ResponseSpec actualResponse = doDelete(BASE_URL + "/" + Long.MAX_VALUE);

        //THEN
        actualResponse.expectStatus().isNotFound();
    }

    private EmployeeDto createDummyEmployee() {
        return doPostWithBody(BASE_URL, employeeDto)
                .expectStatus()
                .isOk().expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();
    }

    private List<EmployeeDto> getAllEmployees() {
        return webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult()
                .getResponseBody();
    }
}
