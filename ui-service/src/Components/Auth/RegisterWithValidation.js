
import React from "react";
import { render } from "react-dom";
import { Formik } from "formik";
import * as Yup from "yup";

const App = () => (
  <div className="app">
    <h1> Register </h1>
    <Formik
      initialValues={{
        email: "",
        firstname: "",
        surname: "",
        addressLine: "",
        postcode: "",
        password: "",
        confirmPassword: ""
      }}
      onSubmit={(values, { setSubmitting }) => {
        setTimeout(() => {
          alert(JSON.stringify(values, null, 2));
          setSubmitting(false);
        }, 500);
      }}
      validationSchema={Yup.object().shape({
        email: Yup.string().email().required("Required"),
        firstname: Yup.string().required("Firstname Required"),
        surname: Yup.string().required("Surname Required"),
        addressLine: Yup.string().required(" Address Required "),
        postcode: Yup.string().required(" Postcode Required")
      })}
    >
      {props => {
        const {
          values,
          touched,
          errors,
          dirty,
          isSubmitting,
          handleChange,
          handleBlur,
          handleSubmit
        } = props;
        return (
          <form onSubmit={handleSubmit}>

            <label htmlFor="firstname" style={{ display: "block" }}>
              Firstname
            </label>
            <input
              id="firstname"
              type="text"
              value={values.firstname}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.firstname && touched.firstname
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.firstname && touched.firstname && (
              <div className="input-feedback">{errors.firstname}</div>
            )}

            <label htmlFor="surname" style={{ display: "block" }}>
              Surname
            </label>
            <input
              id="surname"
              type="text"
              value={values.surname}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.surname && touched.surname
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.surname && touched.surname && (
              <div className="input-feedback">{errors.surname}</div>
            )}

            <label htmlFor="email" style={{ display: "block" }}>
              Email
            </label>
            <input
              id="email"
              type="text"
              value={values.email}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.email && touched.email
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.email && touched.email && (
              <div className="input-feedback">{errors.email}</div>
            )}

            <label htmlFor="addressline" style={{ display: "block" }}>
              Address Line
            </label>
            <input
              id="addressLine"
              type="text"
              value={values.addressLine}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.addressLine && touched.addressLine
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.addressLine && touched.addressLine && (
              <div className="input-feedback">{errors.addressLine}</div>
            )}

            <label htmlFor="postcode" style={{ display: "block" }}>
              Postcode
            </label>
            <input
              id="postcode"
              type="text"
              value={values.postcode}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.postcode && touched.postcode
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.postcode && touched.postcode && (
              <div className="input-feedback">{errors.postcode}</div>
            )}

            <label htmlFor="password" style={{ display: "block" }}>
              Password
            </label>
            <input
              id="password"
              type="text"
              value={values.password}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.password && touched.password
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.password && touched.password && (
              <div className="input-feedback">{errors.password}</div>
            )}

            <label htmlFor="confirmPassword" style={{ display: "block" }}>
              Confirm Password
            </label>
            <input
              id="confirmPassowrd"
              type="text"
              value={values.confirmPassword}
              onChange={handleChange}
              onBlur={handleBlur}
              className={
                errors.confirmPassword && touched.confirmPassword
                  ? "text-input error"
                  : "text-input"
              }
            />
            {errors.confirmPassword && touched.confirmPassword && (
              <div className="input-feedback">{errors.confirmPassword}</div>
            )}

            <button type="submit" disabled={isSubmitting}>
              Submit
            </button>
          </form>
        );
      }}
    </Formik>
  </div>
);

render(<App />, document.getElementById("root"));
