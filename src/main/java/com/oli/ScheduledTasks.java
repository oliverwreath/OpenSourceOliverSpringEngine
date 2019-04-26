package com.oli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author: Oliver
 */
@Slf4j
@Component
public class ScheduledTasks {
//    @Autowired
//    private CaseService caseService;
//    @Autowired
//    private RemovalAuditService removalAuditService;
//    @Autowired
//    private LoginAuditService loginAuditService;
//    @Autowired
//    private TransactionService transactionService;
////    @Autowired
////    private ContactService contactService;
//    @Autowired
//    private LeadService leadService;
//    @Autowired
//    private LeadConversationsService leadConversationsService;
//    @Autowired
//    private ClientService clientService;
//    @Autowired
//    private ClientConversationsService clientConversationsService;
//    @Autowired
//    private CaseAssignedToEmployeeService caseAssignedToEmployeeService;
//    @Autowired
//    private CaseOpenedByEmployeeService caseOpenedByEmployeeService;

    private static final int ONE_SECOND = 1000;
    private static final int TEN_SECONDS = 10 * ONE_SECOND;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int TEN_MINUTES = 10 * ONE_MINUTE;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final int ONE_DAY = 24 * ONE_HOUR;
    private static final String CRON_3AM_DAILY = "0 0 3 * * *";//3:00 AM every day
    private static final String CRON_6AM_DAILY = "0 0 6 * * *";//6:00 AM every day
    private static final long LOGIN_AUDIT_RETENTION_DAYS = 30L;
    private static final long REMOVAL_AUDIT_RETENTION_DAYS = 20L;

    /**
     * Don't touch the code, important yet delicate:
     * schedule everything else
     */
    @Scheduled(fixedDelay = ONE_HOUR, initialDelay = ONE_SECOND)
    public void scheduler() {
//        syncContactFromClient2Lead();
//        updateTransaction_with_CADAmounts();
//        updateCase_with_CollectedFees();
//        updateJoinTables_with_EmployeeName();
    }

    @Scheduled(fixedDelay = ONE_SECOND, initialDelay = ONE_SECOND)
    public void scheduleFixedRateWithInitialDelayTask() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task with one second initial delay - " + now);
    }

//    /**
//     * Don't touch the code, important yet delicate:
//     * syncContactFromClient2Lead
//     */
//    @Transactional
//    public void syncContactFromClient2Lead() {
//        Iterable<Client> clients = clientService.findAll();
//        Map<UUID, EmbeddedContact> map = new HashMap<>();
//        for (Client client : clients) {
//            map.put(client.getId(), client.getContact());
//        }
//
//        Iterable<Lead> leads = leadService.findAll();
//        for (Lead lead : leads) {
//            lead.setContact(map.get(lead.getId()));
//        }
//        leadService.saveAll(leads);
//    }
//
//    /**
//     * Don't touch the code, important yet delicate:
//     * update all transactions for AmountCAD
//     */
//    @Transactional
//    public void updateTransaction_with_CADAmounts() {
//        log.debug("updateTransaction_with_CADAmounts Begin");
//        Iterable<Transaction> all = transactionService.findAll();
//        for (Transaction transaction : all) {
//            transaction.setTransactionAmountCAD(transaction.getTransactionAmount().multiply(getCurrencyFactor(transaction.getTransactionSymbol())));
//        }
//
//        transactionService.saveAll(all);
//        log.debug("updateTransaction_with_CADAmounts End");
//    }
//
//    /**
//     * Don't touch the code, important yet delicate:
//     * update all cases for CollectedFees (in CAD)
//     */
//    @Transactional
//    public void updateCase_with_CollectedFees() {
//        log.debug("updateCase_with_CollectedFees Begin");
//        Iterable<CaseEntity> all = caseService.findAllJoinFetchTransactions();
//        for (CaseEntity caseEntity : all) {
//            List<Transaction> transactions = caseEntity.getTransactions();
//            BigDecimal sum = BigDecimal.ZERO;
//            BigDecimal sumSpent = BigDecimal.ZERO;
//            for (Transaction transaction : transactions) {
//                if (transaction.getIsInbound()) {
//                    sum = sum.add(transaction.getTransactionAmountCAD());
//                } else {
//                    sumSpent = sumSpent.add(transaction.getTransactionAmountCAD());
//                }
//            }
//            caseEntity.setFeeCollected(sum);
//            caseEntity.setMoneySpent(sumSpent);
//            caseEntity.setFeeOwe(caseEntity.getFeeToCharge().subtract(sum));
//        }
//
//        caseService.saveAll(all);
//        log.debug("updateCase_with_CollectedFees End");
//    }
//
//    /**
//     * Don't touch the code, important yet delicate:
//     * update join tables with employee name
//     */
//    @Transactional
//    public void updateJoinTables_with_EmployeeName() {
//        log.debug("updateJoinTables_with_EmployeeName Begin");
//        Iterable<CaseAssignedToEmployee> caseAssignedToEmployees = caseAssignedToEmployeeService.findAllJoinFetchEmployee();
//        for (CaseAssignedToEmployee caseAssignedToEmployee : caseAssignedToEmployees) {
//            if (caseAssignedToEmployee.getAssignedEmployee() != null && StringUtils.isNotBlank(caseAssignedToEmployee.getAssignedEmployee().getName())) {
//                caseAssignedToEmployee.setEmployeeName(caseAssignedToEmployee.getAssignedEmployee().getName());
//            }
//        }
//        caseAssignedToEmployeeService.saveAll(caseAssignedToEmployees);
//
//        Iterable<CaseOpenedByEmployee> caseOpenedByEmployees = caseOpenedByEmployeeService.findAllJoinFetchEmployee();
//        for (CaseOpenedByEmployee caseOpenedByEmployee : caseOpenedByEmployees) {
//            if (caseOpenedByEmployee.getOpenedEmployee() != null && StringUtils.isNotBlank(caseOpenedByEmployee.getOpenedEmployee().getName())) {
//                caseOpenedByEmployee.setEmployeeName(caseOpenedByEmployee.getOpenedEmployee().getName());
//            }
//        }
//        caseOpenedByEmployeeService.saveAll(caseOpenedByEmployees);
//        log.debug("updateJoinTables_with_EmployeeName End");
//    }
//
//    /**
//     * Don't touch the code, important yet delicate:
//     * daily - remove outstanding audit.
//     */
//    @Scheduled(cron = CRON_3AM_DAILY)
//    @Transactional
//    public void removeOutstandingAudit() {
//        loginAuditService.deleteByCreatedOnBefore(LocalDateTime.now().minusDays(LOGIN_AUDIT_RETENTION_DAYS));
//        removalAuditService.deleteByRemovedOnBefore(LocalDateTime.now().minusDays(REMOVAL_AUDIT_RETENTION_DAYS));
//    }
}
