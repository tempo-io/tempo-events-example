package com.tempoplugin.events

import com.atlassian.event.api.EventListener
import com.atlassian.event.api.EventPublisher
import com.atlassian.jira.event.worklog.WorklogEvent
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.tempoplugin.account.event.api.AccountCreatedEvent
import com.tempoplugin.account.event.api.AccountDeletedEvent
import com.tempoplugin.account.event.api.AccountEvent
import com.tempoplugin.account.event.api.AccountUpdatedEvent
import com.tempoplugin.budget.event.api.ExpenseCreatedEvent
import com.tempoplugin.budget.event.api.ExpenseDeletedEvent
import com.tempoplugin.budget.event.api.ExpenseEvent
import com.tempoplugin.budget.event.api.ExpenseUpdatedEvent
import com.tempoplugin.budget.event.api.FolioCreatedEvent
import com.tempoplugin.budget.event.api.FolioDeletedEvent
import com.tempoplugin.budget.event.api.FolioEvent
import com.tempoplugin.budget.event.api.FolioUpdatedEvent
import com.tempoplugin.budget.event.api.PortfolioCreatedEvent
import com.tempoplugin.budget.event.api.PortfolioDeletedEvent
import com.tempoplugin.budget.event.api.PortfolioEvent
import com.tempoplugin.budget.event.api.PortfolioUpdatedEvent
import com.tempoplugin.budget.event.api.PositionCreatedEvent
import com.tempoplugin.budget.event.api.PositionDeletedEvent
import com.tempoplugin.budget.event.api.PositionEvent
import com.tempoplugin.budget.event.api.PositionUpdatedEvent
import com.tempoplugin.category.event.api.CategoryCreatedEvent
import com.tempoplugin.category.event.api.CategoryDeletedEvent
import com.tempoplugin.category.event.api.CategoryEvent
import com.tempoplugin.category.event.api.CategoryUpdatedEvent
import com.tempoplugin.customer.event.api.CustomerCreatedEvent
import com.tempoplugin.customer.event.api.CustomerDeletedEvent
import com.tempoplugin.customer.event.api.CustomerEvent
import com.tempoplugin.customer.event.api.CustomerUpdatedEvent
import com.tempoplugin.planner.event.api.AllocationCreatedEvent
import com.tempoplugin.planner.event.api.AllocationDeletedEvent
import com.tempoplugin.planner.event.api.AllocationEvent
import com.tempoplugin.planner.event.api.AllocationUpdatedEvent
import com.tempoplugin.team.event.api.TeamCreatedEvent
import com.tempoplugin.team.event.api.TeamDeletedEvent
import com.tempoplugin.team.event.api.TeamEvent
import com.tempoplugin.team.event.api.TeamUpdatedEvent
import com.tempoplugin.timesheet.approval.api.Approval
import com.tempoplugin.timesheet.event.api.ApprovalEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TempoEventListener
@Autowired
constructor(@param:ComponentImport
            private val eventPublisher: EventPublisher) : InitializingBean, DisposableBean {

    private val logger = LoggerFactory.getLogger(TempoEventListener::class.java)

    override fun afterPropertiesSet() {
        eventPublisher.register(this)
    }

    override fun destroy() {
        eventPublisher.unregister(this)
    }

    /**
     * This will listen to any Tempo events : the type of the event will indicate the nature of the
     * event. Best would be to listen to specific event (ex: AccountEvent or AccoundCreatedEvent)
     */
    /*
    @EventListener
    fun onTempoEvent(event: Event) {

        logger.info("Received event of type ${event.javaClass}")
    }
    */

    /**
     * This is a way to listen to all the <code>AccountEvent</code>s.
     */
    @EventListener
    fun onAccountEvent(event: AccountEvent) {

        when (event) {
            is AccountCreatedEvent -> {
                logger.info("A new account has been created : '${event.account.name}'")
            }
            is AccountUpdatedEvent -> {
                logger.info("The account '${event.account.name}' has been updated!")
            }
            is AccountDeletedEvent -> {
                logger.info("Account '${event.account.name}' has been deleted!")
            }
        }
    }

    /**
     * This is a way to listen to <code>AccountCreatedEvent</code> only.
     */
    @EventListener
    fun onAccountCreatedEvent(event: AccountCreatedEvent) {

        logger.info("A new account has been created : '${event.account.name}'")
    }

    /**
     * This is a way to listen to <code>AccountUpdatedEvent</code> only.
     */
    @EventListener
    fun onAccountChangedEvent(event: AccountUpdatedEvent) {

        logger.info("The account '${event.account.name}' has been updated!")
    }

    /**
     * This is a way to listen to <code>AccountDeletedEvent</code> only.
     */
    @EventListener
    fun onAccountDeletedEvent(event: AccountDeletedEvent) {

        logger.info("Account '${event.account.name}' has been deleted!")
    }

    @EventListener
    fun onAllocationEvent(event: AllocationEvent) {

        when (event) {
            is AllocationCreatedEvent -> {
                logger.info("A new allocation has been created : '${event.allocation.id}'")
            }
            is AllocationUpdatedEvent -> {
                logger.info("The allocation '${event.allocation.id}' has been updated!")
            }
            is AllocationDeletedEvent -> {
                logger.info("Allocation '${event.allocation.id}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onCategoryEvent(event: CategoryEvent) {

        when (event) {
            is CategoryCreatedEvent -> {
                logger.info("A new category has been created : '${event.category.name}'")
            }
            is CategoryUpdatedEvent -> {
                logger.info("The category '${event.category.name}' has been updated!")
            }
            is CategoryDeletedEvent -> {
                logger.info("Category '${event.category.name}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onCustomerEvent(event: CustomerEvent) {

        when (event) {
            is CustomerCreatedEvent -> {
                logger.info("A new customer has been created : '${event.customer.name}'")
            }
            is CustomerUpdatedEvent -> {
                logger.info("The customer '${event.customer.name}' has been updated!")
            }
            is CustomerDeletedEvent -> {
                logger.info("Customer '${event.customer.name}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onFolioEvent(event: FolioEvent) {

        when (event) {
            is FolioCreatedEvent -> {
                logger.info("A new folio has been created : '${event.folio.id}'")
            }
            is FolioUpdatedEvent -> {
                logger.info("The folio '${event.folio.id}' has been updated!")
            }
            is FolioDeletedEvent -> {
                logger.info("Folio '${event.folio.id}' has been deleted (${event.deletionType})!")
            }
        }
    }

    @EventListener
    fun onPortfolioEvent(event: PortfolioEvent) {

        when (event) {
            is PortfolioCreatedEvent -> {
                logger.info("A new portfolio has been created : '${event.portfolio.id}'")
            }
            is PortfolioUpdatedEvent -> {
                logger.info("The portfolio '${event.portfolio.id}' has been updated!")
            }
            is PortfolioDeletedEvent -> {
                logger.info("Portfolio '${event.portfolio.id}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onFolioExpenseEvent(event: ExpenseEvent) {

        when (event) {
            is ExpenseCreatedEvent -> {
                logger.info("A new expense has been created : '${event.expense.id}'")
            }
            is ExpenseUpdatedEvent -> {
                logger.info("The expense '${event.expense.id}' has been updated!")
            }
            is ExpenseDeletedEvent -> {
                logger.info("Expense '${event.expense.id}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onFolioPositionEvent(event: PositionEvent) {

        when (event) {
            is PositionCreatedEvent -> {
                logger.info("A new position has been created : '${event.position.id}'")
            }
            is PositionUpdatedEvent -> {
                logger.info("The position '${event.position.id}' has been updated!")
            }
            is PositionDeletedEvent -> {
                logger.info("Position '${event.position.id}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onTeamEvent(event: TeamEvent) {

        when (event) {
            is TeamCreatedEvent -> {
                logger.info("A new team has been created : '${event.team.name}'")
            }
            is TeamUpdatedEvent -> {
                logger.info("The team '${event.team.name}' has been updated!")
            }
            is TeamDeletedEvent -> {
                logger.info("Team '${event.team.name}' has been deleted!")
            }
        }
    }

    @EventListener
    fun onTimesheetApprovalEvent(event: ApprovalEvent) {

        when (event.approval.action) {
            Approval.Action.submit -> {
                logger.info("Timesheet of ${event.approval.actorKey} has been submitted!")
            }
            Approval.Action.approve -> {
                logger.info("Timesheet of ${event.approval.actorKey} is now approved!")
            }
            Approval.Action.reject -> {
                logger.info("Timesheet of ${event.approval.actorKey} has been rejected!")
            }
            Approval.Action.reopen -> {
                logger.info("Timesheet of ${event.approval.actorKey} has been re-opened!")
            }
            else -> {
                logger.error("Received unknown action '${event.approval.action}'")
            }
        }
    }

    @EventListener
    fun onWorklogEvent(event: WorklogEvent) {

        logger.info("Received event of type ${event.javaClass}")
    }
}
