package com.backend.pfe.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl {

    // مفتاح سري مشفر بتنسيق Base64 يستخدم لتوقيع وفك تشفير JWT
    private static final String SECRET_KEY = "U0cuU1NBUFA=";

    // طريقة لإنشاء JWT باستخدام تفاصيل المستخدم
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // تعيين اسم المستخدم كموضوع للتوكن
                .setIssuedAt(new Date(System.currentTimeMillis())) // تعيين وقت إصدار التوكن
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // تعيين وقت انتهاء صلاحية التوكن (10 ساعات)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // توقيع التوكن باستخدام المفتاح السري
                .compact(); // إنشاء التوكن بشكل مضغوط
    }

    // طريقة لاستخراج اسم المستخدم من التوكن
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // استخراج الموضوع (اسم المستخدم) من التوكن
    }

    // طريقة عامة لاستخراج أي معلومة من التوكن باستخدام دالة محددة
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // استخراج جميع المعلومات من التوكن
        return claimsResolver.apply(claims); // تطبيق الدالة المحددة لاستخراج المعلومة المطلوبة
    }

    // طريقة لإنشاء مفتاح التوقيع من المفتاح السري
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // فك تشفير المفتاح السري من Base64
        return Keys.hmacShaKeyFor(keyBytes); // إنشاء مفتاح التوقيع
    }

    // طريقة لاستخراج جميع المعلومات من التوكن
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // تعيين مفتاح التوقيع
                .build()
                .parseClaimsJws(token) // تحليل التوكن
                .getBody(); // استخراج المعلومات (Claims) من التوكن
    }
}