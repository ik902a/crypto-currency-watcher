package com.idfinance.task.service;

import com.idfinance.task.entity.Price;
import com.idfinance.task.entity.User;
import com.idfinance.task.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CheckService {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private final UserRepository userRepository;

    public void checkPercent(Price newPrice) {
        List<User> userList = userRepository.findUserByCurrency(newPrice.getCurrency().getId());
        for (User user : userList) {
            BigDecimal percent = newPrice.getPrice()
                    .divide(user.getPrice().getPrice(), 2, RoundingMode.HALF_UP)
                    .multiply(ONE_HUNDRED)
                    .subtract(ONE_HUNDRED);
            BigDecimal percentInfo = percent;
            log.info("Old price={}, new price={}, percent={}",
                    user.getPrice().getPrice(),
                    newPrice.getPrice(),
                    percentInfo);
            if (percent.abs().compareTo(BigDecimal.ONE) > 0 ) {
                log.warn("The Price of Currency {} for {} has changed by {}% since registration",
                        user.getPrice().getCurrency().getSymbol(),
                        user.getUsername(),
                        percentInfo);
            }
        }
    }
}
