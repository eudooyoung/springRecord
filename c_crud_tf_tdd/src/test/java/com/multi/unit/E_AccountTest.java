package com.multi.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class E_AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account(100);
    }

    @Test
    @DisplayName("현재 잔액이 정확한지 확인")
    void testBalance() {
        assertThat(account.getBalance()).as("초기값 잔액은 100 이어야 함").isEqualTo(100);
    }

    @Test
    @DisplayName("입금 후 잔액이 증가했는지 확인")
    void testDeposit() {
        // when
        account.deposit(100);
        // then
        assertThat(account.getBalance()).as("입금 후 잔액은 200이어야 함").isEqualTo(200);
    }

    @Test
    @DisplayName("출금 후 잔액이 감소했는지 확인")
    void testWithdraw() {
        // when
        account.withdraw(50);
        // then
        assertThat(account.getBalance()).as("출금 후 잔액은 50이어야 함").isEqualTo(50);
    }

    @Test
    @DisplayName("잔액보다 더 큰 금액 출금시 에러 발생")
    void testException(){
        assertThatThrownBy(() -> account.withdraw(150))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔액이 부족합니다.");
    }
}

class Account {

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    // 입금
    public void deposit(int money) {
        balance += money;
    }

    // 출금
    public void withdraw(int money) {
        if (money > balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        balance -= money;
    }
}
