package ru.avishnyakov.concurrency.atomic;

import ru.avishnyakov.concurrency.NotThreadSafe;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

@NotThreadSafe
public class UnsafeCountingFactorizer extends HttpServlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);

        ++count;

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
