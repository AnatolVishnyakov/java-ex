package ru.avishnyakov.concurrency.atomic;

import ru.avishnyakov.concurrency.ThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CountingFactorizer extends HttpServlet {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);

        count.incrementAndGet();

//        encodeIntoResponse(resp, factors);
    }

    private BigInteger[] factor(BigInteger number) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return Optional.ofNullable(req.getParameter("number"))
                .map(BigInteger::new)
                .orElse(BigInteger.ZERO);
    }
}
