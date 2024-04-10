import React from 'react'
import AddressCArd from '../AddressCard/AddressCard'
import { Button,Grid,TextField,Box } from '@mui/material';
const DeliveryAddressForm = () => {
    const handleSubmit=(e)=>
    {
        console.log("address")
        e.preventDefault();
        const data=new FormData(e.currentTarget);
        const address={

            firstName:data.get("firstName"),
            lastName:data.get("lastName"),
            streetAddress:data.get("address"),
            city:data.get("city"),
            state:data.get("state"),
            zipCode:data.get("zip"),
            mobile:data.get("phoneNumber")
            }
            
        console.log("address",data)
    }

    return (
        <div>
            <Grid container spacing={4}>
                <Grid xs={12} lg={7} className="border rounded-e-md shadow-md h-[30.5rem] overflow-y-scroll">
                    <div className="p-5 py-7 border-b cursor-pointer">
                        <AddressCArd />
                        <Button
                            sx={{ mt: 2, bgcolor: "RGB(145 85 253)" }}
                            size="large"
                            variant="contained"
                        >
                            Deliver Here
                        </Button>
                    </div>
                </Grid>
                <Grid item xs={12} lg={5}>
                    <Box className="border rounded-s-md shadow-md p-5">
                        <form onSubmit={handleSubmit}>
                            <Grid container spacing={3}>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="firstName"
                                        name="firstName"
                                        label="First Name"
                                        fullWidth

                                        autocomplete="given-name"
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="lasrName"
                                        name="lasttName"
                                        label="Last Name"
                                        fullWidth
                                        autocomplete="given-name"
                                    />
                                </Grid>
                                <Grid item xs={12} >
                                    <TextField

                                        required
                                        id="address"
                                        name="address"
                                        label="Address"
                                        fullWidth
                                        autocomplete="given-name"
                                        multiline
                                        rows={4}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="city"
                                        name="city"
                                        label="City"
                                        fullWidth
                                        autocomplete="given-name"
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="state"
                                        name="state"
                                        label="State/Province/Region"
                                        fullWidth
                                        autocomplete="given-name"
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="zip"
                                        name="zip"
                                        label="Zip/ Postal Code"
                                        fullWidth
                                        autocomplete="shipping postal-code"
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField

                                        required
                                        id="phoneNumber"
                                        name="phoneNumber"
                                        label="Phone Number"
                                        fullWidth
                                        autocomplete="given-name"
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <Button
                                        sx={{ py:1.5,mt: 2, bgcolor: "RGB(145 85 253)" }}
                                        size="large"
                                        variant="contained"
                                        type="submit"
                                    >
                                        Deliver Here
                                    </Button>
                                </Grid>

                            </Grid>
                        </form>
                    </Box>

                </Grid>
            </Grid>

        </div >
    )
}
export default DeliveryAddressForm
